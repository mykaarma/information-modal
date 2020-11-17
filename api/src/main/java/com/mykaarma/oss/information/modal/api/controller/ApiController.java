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
package com.mykaarma.oss.information.modal.api.controller;

import com.mykaarma.oss.information.modal.api.service.ApiService;
import com.mykaarma.oss.information.modal.model.dto.ModalResponse;
import com.mykaarma.oss.information.modal.model.dto.ModuleModalResponse;
import com.mykaarma.oss.information.modal.model.dto.ModuleResponse;
import com.mykaarma.oss.information.modal.model.utils.RestURIConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "InformationModal API Controller", produces = RestURIConstants.JSON_MIME_TYPE,
        consumes = RestURIConstants.JSON_MIME_TYPE)
@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin(origins ="*")
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * "Get Modal Json for a module or all Modules. If no Module is passed all moduels are returned.
     * @param moduleName
     * @return ModuleModalResponse
     */
    @ApiOperation("Get Modal Json for a module or all Modules")
    @GetMapping(value = "/moduleModalInfo")
    public ModuleModalResponse getModuleModalInfo(@Param(value = RestURIConstants.MODULE) String moduleName){
        return apiService.getModuleModalResponse(moduleName);
    }

    /**
     * Get Modal Json given UUID or Identification Tag
     * @param idTag
     * @param uuid
     * @return ModalResponse
     */
    @ApiOperation("Get Modal Json given UUID or Identification Tag")
    @GetMapping(value = "/modalInfo")
    public ModalResponse getModalInfo(@Param(value = RestURIConstants.IDENTIFICATION_TAG) String idTag,
                                      @Param(value = RestURIConstants.MODAL_UUID) String uuid){
        return apiService.getModalResponse(idTag, uuid);
    }

    /**
     * Get All Modules
     * @return ModuleResponse
     */
    @ApiOperation("Get All Modules")
    @GetMapping(value = "/modules")
    public ModuleResponse getModules(){
        return apiService.getModules();
    }
}
