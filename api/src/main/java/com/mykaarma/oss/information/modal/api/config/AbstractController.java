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
package com.mykaarma.oss.information.modal.api.config;


import com.mykaarma.oss.information.modal.model.dto.ResponseDTO;
import com.mykaarma.oss.information.modal.model.enums.ErrorCodes;
import com.mykaarma.oss.information.modal.model.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 *  Global Exception Handling for any uncaught/thrown Exception
 *
 */
@Slf4j
@RestControllerAdvice
public class AbstractController {

    /**
     * Handle Uncaught Exception, add `INTERNAL_SERVER_EXCEPTION` Error to the response and Return 500 Status code
     * @param httpServletRequest Request
     * @param httpServletResponse Response
     * @param e Exception
     * @return Response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseDTO handleBaseException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                           Exception e) {
        log.error("Exception occurred : " + e.getMessage(), e);
        ResponseDTO response = new ResponseDTO();
        response.setErrors(Collections.singleton(new ErrorDTO(ErrorCodes.INTERNAL_SERVER_EXCEPTION)));
        response.setWarnings(Collections.EMPTY_SET);
        return response;
    }
}
