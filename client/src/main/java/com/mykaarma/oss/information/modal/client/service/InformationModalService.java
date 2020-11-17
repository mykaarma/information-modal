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
package com.mykaarma.oss.information.modal.client.service;

import com.mykaarma.oss.information.modal.model.dto.ModalResponse;
import com.mykaarma.oss.information.modal.model.dto.ModuleModalResponse;
import com.mykaarma.oss.information.modal.model.dto.ModuleResponse;
import com.mykaarma.oss.information.modal.model.utils.RestURIConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InformationModalService {

    @GET("moduleModalInfo")
    Call<ModuleModalResponse> getModuleModalInfo (@Query(RestURIConstants.MODULE) String module);

    @GET("modalInfo")
    Call<ModalResponse> getModalInfo (@Query(RestURIConstants.IDENTIFICATION_TAG) String idTag,
                                      @Query(RestURIConstants.MODAL_UUID) String uuid);

    @GET("modules")
    Call<ModuleResponse> getModules ();
}
