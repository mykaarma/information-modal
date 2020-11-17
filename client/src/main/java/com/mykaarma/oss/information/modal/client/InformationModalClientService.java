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
package com.mykaarma.oss.information.modal.client;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mykaarma.oss.information.modal.client.service.InformationModalService;
import com.mykaarma.oss.information.modal.model.dto.ModalResponse;
import com.mykaarma.oss.information.modal.model.dto.ModuleModalResponse;
import com.mykaarma.oss.information.modal.model.dto.ModuleResponse;
import com.mykaarma.oss.information.modal.model.dto.ResponseDTO;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class InformationModalClientService {


    private final static ObjectMapper mapper = new ObjectMapper();
    private InformationModalService informationModalService;

    /**
     * Creates RetrofitInstance with the API Url given
     * @param baseUrl
     */
    public InformationModalClientService(String baseUrl) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).
                writeTimeout(30, TimeUnit.SECONDS);
        Retrofit jsonRetrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
                JacksonConverterFactory.create(mapper)).client(httpClient.build()).build();
        informationModalService = jsonRetrofit.create(InformationModalService.class);
    }


    /**
     * Utility method to parse ResponseBody (if successful) and ErrorBody (if request failed)
     * @param retrofitResponse
     * @param responseClassName
     * @return Response of the type responseClassName which extends ResponseDTO
     * @throws Exception
     */
    public  <T extends ResponseDTO> T processResponse(Response<T> retrofitResponse,
                                                      Class<T> responseClassName) throws Exception{
        if(retrofitResponse.isSuccessful()){
            return retrofitResponse.body();
        } else {
            String errorBody = retrofitResponse.errorBody().string();
            return mapper.readValue(errorBody, responseClassName);
        }
    }

    /**
     * Get Modal Info for a given Module or All Modules (if null module is passed)
     * @param module
     * @return
     * @throws Exception
     */
    public ModuleModalResponse getModuleModalInfo (String module) throws Exception {
        Response<ModuleModalResponse> response = informationModalService.getModuleModalInfo(module).execute();
        return processResponse(response,ModuleModalResponse.class);
    }

    /**
     *
     *  Get ModalInfo given it's IdTag Or UUID. One of idTag and UUID should be passed.
     * @param idTag
     * @param uuid
     * @return
     * @throws Exception
     */
    public ModalResponse getModalInfo (String idTag, String uuid) throws Exception {
        if((idTag==null || idTag.isEmpty()) && (uuid == null || uuid.isEmpty())){
            throw new Exception("At least one of idTag and UUID is needeed");
        }
        Response<ModalResponse> response = informationModalService.getModalInfo(idTag, uuid).execute();
        return processResponse(response,ModalResponse.class);
    }


    /**
     *  Get the list of Modules
     * @return
     * @throws Exception
     */
    public ModuleResponse getModules ()throws Exception {
        Response<ModuleResponse> response = informationModalService.getModules().execute();
        return processResponse(response,ModuleResponse.class);
    }
}
