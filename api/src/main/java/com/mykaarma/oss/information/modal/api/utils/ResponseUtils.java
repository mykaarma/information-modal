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
package com.mykaarma.oss.information.modal.api.utils;

import com.mykaarma.oss.information.modal.model.dto.WarningDTO;
import com.mykaarma.oss.information.modal.model.enums.WarningCodes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utility class to Create final Response
 */
public class ResponseUtils {

    /**
     * Convert the Warnings to the format needed for Response.
     * Adds the Warning Context info to the Response.
     * @param warnings
     * @return
     */
    public static Set<WarningDTO> getWarningResponse(Map<WarningCodes, String> warnings) {
        Set<WarningDTO> warningResponse = new HashSet<>();
        if (warnings != null && !warnings.isEmpty()) {

            for(WarningCodes warningCodes: warnings.keySet()) {
                WarningDTO warningDTO = new WarningDTO(warningCodes);
                if (warnings.get(warningCodes) != null) {
                    warningDTO.setWarningDescription(warningDTO.getWarningDescription() + warnings.get(warningCodes));
                }
                warningResponse.add(warningDTO);
            }
        }
        return warningResponse;
    }

    /**
     * Utility Method to add Warning with Optional Context Information
     * @param warnings
     * @param warningCodes
     * @param paramValue
     */
    public static void addWarning(Map<WarningCodes, String> warnings,WarningCodes warningCodes, String paramValue) {
        if (warnings.get(warningCodes) == null) {
            warnings.put(warningCodes, paramValue);
        } else {
            String value = warnings.get(warningCodes) + ", " + paramValue;
            warnings.put(warningCodes, value);
        }
    }
}
