package com.intecap.Apibooks.response;

public class CategoriaResponseRest extends ResponseRest{ // ResponseRest: contiene toda la estructura de  la metada la respuesta de la api

    private CategoriaResponse categoriaResponse = new CategoriaResponse(); // devolvera una lista de categorias en formato json con la estructura de la clase CategoriaResponse y la metadata de la clase ResponseRest

    public CategoriaResponse getCategoriaResponse() {
        return categoriaResponse;
    }

    public void setCategoriaResponse(CategoriaResponse categoriaResponse) {
        this.categoriaResponse = categoriaResponse;
    }
}
