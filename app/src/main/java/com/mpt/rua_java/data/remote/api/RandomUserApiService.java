package com.mpt.rua_java.data.remote.api;

import com.mpt.rua_java.data.remote.dto.RandomUserResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface del servicio API para RandomUser
 * Implementa los endpoints según documentación oficial de RandomUser.me
 * Soporta todos los parámetros especificados en los requisitos
 */
public interface RandomUserApiService {

    /**
     * Obtiene usuarios aleatorios de la API RandomUser
     * Endpoint principal según documentación: https://randomuser.me/api/
     *
     * @param results Número de resultados (1-5000, requisito: máximo 100)
     * @param gender Filtro por género: "male", "female"
     * @param nat Código de nacionalidad (AU,BR,CA,CH,DE,DK,ES,FI,FR,GB,IE,IR,NO,NL,NZ,TR,US)
     * @param inc Campos a incluir separados por coma
     * @param exc Campos a excluir separados por coma
     * @param seed Semilla para reproducir resultados
     * @param page Número de página para paginación
     * @param format Formato de respuesta (json por defecto)
     * @return Call con la respuesta de la API
     */

    @GET("api/")
    Call<RandomUserResponseDto> getRandomUsers(
        @Query("results") int results,
        @Query("gender") String gender,
        @Query("nat") String nat,
        @Query("inc") String inc,
        @Query("exc") String exc,
        @Query("seed") String seed,
        @Query("page") Integer page,
        @Query("format") String format
    );

    /**
     * Método simplificado para obtener usuarios con parámetros básicos
     */
    @GET("api/")
    Call<RandomUserResponseDto> getUsers(
        @Query("results") int results,
        @Query("gender") String gender,
        @Query("nat") String nationality,
        @Query("seed") String seed
    );
}
