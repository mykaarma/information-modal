/**
 * Information Modal
 * Copyright (C) 2020 myKaarma.
 * opensource@mykaarma.com
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mykaarma.oss.information.modal.api.service;

import com.mykaarma.oss.information.modal.api.jpa.model.ModalInfo;
import com.mykaarma.oss.information.modal.api.jpa.model.Module;
import com.mykaarma.oss.information.modal.api.jpa.model.ModuleModalInfoMapping;
import com.mykaarma.oss.information.modal.api.jpa.repository.ModalInfoRepository;
import com.mykaarma.oss.information.modal.api.jpa.repository.ModuleModalInfoMappingRepository;
import com.mykaarma.oss.information.modal.api.jpa.repository.ModuleRepository;
import com.mykaarma.oss.information.modal.api.mapper.ModalInfoMapper;
import com.mykaarma.oss.information.modal.api.mapper.ModuleMapper;
import com.mykaarma.oss.information.modal.api.utils.ResponseUtils;
import com.mykaarma.oss.information.modal.model.dto.ErrorDTO;
import com.mykaarma.oss.information.modal.model.dto.ModalInfoDTO;
import com.mykaarma.oss.information.modal.model.dto.ModalResponse;
import com.mykaarma.oss.information.modal.model.dto.ModuleModalInfoDTO;
import com.mykaarma.oss.information.modal.model.dto.ModuleModalResponse;
import com.mykaarma.oss.information.modal.model.dto.ModuleResponse;
import com.mykaarma.oss.information.modal.model.dto.WarningDTO;
import com.mykaarma.oss.information.modal.model.enums.ErrorCodes;
import com.mykaarma.oss.information.modal.model.enums.WarningCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ApiService {

    private final ModuleRepository moduleRepository;

    private final ModuleModalInfoMappingRepository moduleModalInfoMappingRepository;

    private final ModalInfoMapper modalInfoMapper;

    private final ModalInfoRepository modalInfoRepository;

    private final ModuleMapper moduleMapper;

    @Autowired
    public ApiService(ModuleRepository moduleRepository, ModuleModalInfoMappingRepository moduleModalInfoMappingRepository, ModalInfoMapper modalInfoMapper, ModalInfoRepository modalInfoRepository, ModuleMapper moduleMapper) {
        this.moduleRepository = moduleRepository;
        this.moduleModalInfoMappingRepository = moduleModalInfoMappingRepository;
        this.modalInfoMapper = modalInfoMapper;
        this.modalInfoRepository = modalInfoRepository;
        this.moduleMapper = moduleMapper;
    }

    /**
     *  Get ModalInfo for moduleName, if moduleName is null returns ModalInfo of all Modules.
     * @param moduleName
     * @return ModuleModalResponses
     */
    public ModuleModalResponse getModuleModalResponse(String moduleName){
        Set<ErrorDTO> errorDTOS = new HashSet<>();
        HashMap<WarningCodes, String> warnings = new HashMap<>();
        HashMap<String,ModuleModalInfoDTO> modalInfoDTOHashMap = new HashMap<>();
        ModuleModalResponse response = new ModuleModalResponse();
        if(moduleName!=null && !moduleName.isEmpty()){
            Module module = moduleRepository.findFirstByNameIgnoreCase(moduleName);
            if(module == null){
                errorDTOS.add(new ErrorDTO(ErrorCodes.INVALID_MODULE));
            } else {
                modalInfoDTOHashMap.put(moduleName, getModalsForSingleModule(module, warnings));
            }
        } else {
            ResponseUtils.addWarning(warnings,WarningCodes.MODULE_NOT_SENT_IN_REQUEST,null );
            List<Module> modules = moduleRepository.findAll();
            if(modules!=null && !modules.isEmpty()){
                modules.forEach(module ->
                        modalInfoDTOHashMap.put(module.getName(), getModalsForSingleModule(module, warnings)));
            } else {
                errorDTOS.add(new ErrorDTO(ErrorCodes.NO_MODULES_CONFIGURED));
            }
        }
        response.setModuleModalInfos(modalInfoDTOHashMap);
        response.setErrors(errorDTOS);
        response.setWarnings(ResponseUtils.getWarningResponse(warnings));
        return response;
    }


    /**
     * Get ModalInfo for given IdTag or UUID.
     * @param idTag
     * @param uuid
     * @return ModalResponse
     */
    public ModalResponse getModalResponse(String idTag, String uuid){
        Set<ErrorDTO> errorDTOS = new HashSet<>();
        Set<WarningDTO> warningDTOS = new HashSet<>();
        ModalResponse modalResponse = new ModalResponse();
        ModalInfoDTO modalInfoDTO = null;
        if((uuid == null || uuid.isEmpty()) && (idTag == null || idTag.isEmpty())){
            errorDTOS.add(new ErrorDTO(ErrorCodes.INVALID_REQUEST_UUID_OR_ID_TOKEN_MISSING));
        } else {
            ModalInfo modalInfo = null;
            if(uuid!=null && !uuid.isEmpty()){
                modalInfo = modalInfoRepository.findFirstByUuidIgnoreCase(uuid);
            }
            else if(idTag!=null && !idTag.isEmpty()){
                modalInfo = modalInfoRepository.findFirstByIdentifierTagIgnoreCase(idTag);
            }
            if(modalInfo == null){
                errorDTOS.add(new ErrorDTO(ErrorCodes.NO_MODAL_EXISTS_FOR_GIVEN_INFO));
            } else {
                modalInfoDTO = modalInfoMapper.map(modalInfo);
            }
        }
        modalResponse.setModal(modalInfoDTO);
        modalResponse.setErrors(errorDTOS);
        modalResponse.setWarnings(warningDTOS);
        return modalResponse;
    }

    /**
     *  Get ModalInfo for given Modules. Adds Warning if no modals are mapped to the module.
     * @param module
     * @param warnings
     * @return ModuleModalInfoDTO
     */
    private ModuleModalInfoDTO getModalsForSingleModule(Module module, HashMap<WarningCodes, String> warnings){
        List<ModuleModalInfoMapping> moduleModalInfoMappings =
                moduleModalInfoMappingRepository.findAllByModuleId(module.getId());
        ModuleModalInfoDTO moduleModalInfoDTO = new ModuleModalInfoDTO();
        moduleModalInfoDTO.setModalInfos(new HashSet<>());
        if(moduleModalInfoMappings == null || moduleModalInfoMappings.isEmpty()){
            ResponseUtils.addWarning(warnings, WarningCodes.NO_MODALS_EXISTS_FOR_MODULE, module.getName());
        } else {
            moduleModalInfoMappings.forEach(moduleModalInfoMapping ->
                    moduleModalInfoDTO.getModalInfos().add(
                            modalInfoMapper.map(moduleModalInfoMapping.getModalInfo())));
        }
        return moduleModalInfoDTO;
    }

    /**
     * Get Modules
     * @return ModuleResponse
     */
    public ModuleResponse getModules() {
        Set<ErrorDTO> errorDTOS = new HashSet<>();
        Set<WarningDTO> warningDTOS = new HashSet<>();
        ModuleResponse moduleResponse = new ModuleResponse();
        List<Module> modules = moduleRepository.findAll();
        if(modules!=null && !modules.isEmpty()){
            moduleResponse.setModules(moduleMapper.map(modules));
        } else {
            errorDTOS.add(new ErrorDTO(ErrorCodes.NO_MODULES_CONFIGURED));
        }
        moduleResponse.setErrors(errorDTOS);
        moduleResponse.setWarnings(warningDTOS);
        return moduleResponse;
    }
}
