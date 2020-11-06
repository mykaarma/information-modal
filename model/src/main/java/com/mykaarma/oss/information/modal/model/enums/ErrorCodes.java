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
package com.mykaarma.oss.information.modal.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCodes {

    INTERNAL_SERVER_EXCEPTION(100001, "INTERNAL_SERVER_EXCEPTION", "Something went wrong on our side"),
    INVALID_MODULE(100002, "INVALID_MODULE", "Invalid module sent in request"),
    NO_MODULES_CONFIGURED(100003, "NO_MODULES_CONFIGURED", "No modules configured"),
    INVALID_REQUEST_UUID_OR_ID_TOKEN_MISSING(10004, "INVALID_REQUEST_UUID_OR_ID_TOKEN_MISSING", "One of UUID, Identifier Token is required"),
    NO_MODAL_EXISTS_FOR_GIVEN_INFO(10005, "NO_MODAL_EXISTS_FOR_GIVEN_INFO", "No modal exists for the given UUID/IdToken"),
    ;

    private Integer errorCode;
    private String errorTitle;
    private String errorDescription;
}
