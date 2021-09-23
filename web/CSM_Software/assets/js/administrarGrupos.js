/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




$(document).ready(function () {
    searchPensums()
});


function searchPensums() {
    $.post('../../../../ControladorPensum?accion=obtenerPensums', {}, function (response) {
        $('#optionPensum').html(response);
        searchMateria();
    });
}

function searchMateria() {
    var pensumCod = $("#optionPensum").val();
    $.post('../../../../ControladorPensum?accion=listarMaterias', {
        pensumCodigo: pensumCod
    }, function (response) {
        $('#optionMateria').html(response);
    });
}