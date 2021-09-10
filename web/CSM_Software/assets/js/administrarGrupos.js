/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




$(document).ready(function () {
    searchPensums()
    searchMateria();
});


function searchPensums() {
    $.post('../../../../ControladorPensum?accion=obtenerPensums', {}, function (response) {
        $('#optionPensum').html(response);
        $.post('../../../../ControladorPensum?accion=listarMaterias', {
            pensumCodigo: $("#optionPensum").val()
        }, function (response) {
            $('#optionMateria').html(response);
        });
    });
}

function searchMateria() {
    console.log("Estoy en searchMateria");
    var p = $("#optionPensum").val();
    console.log(p);
    $.post('../../../../ControladorPensum?accion=listarMaterias', {
        pensumCodigo: p
    }, function (response) {
        $('#optionMateria').html(response);
    });
}