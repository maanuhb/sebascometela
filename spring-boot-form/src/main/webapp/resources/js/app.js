
function validateForm(form) {
    var validate = true;
    var valid = "#00c292";
    var invalid = "red";
    var normal = "rgb(233, 236, 239)";

    $(':input', '#' + form).each(function () {
        if (this.id != "") {


            var $ctrl = $("#" + form).find('[id=' + this.id + ']');
            var key = this.id;
            if (this.required == true) {

                if ($ctrl.is('select')) {
                    if ($("#" + key).val() == "") {
                        $("#" + key).css("border-color", invalid);
                        validate = false;
                    } else {
                        $("#" + key).css("border-color", normal);
                    }
                } else if ($ctrl.is('textarea')) {
                    if ($("#" + key).val() == "") {
                        $("#" + key).css("border-color", invalid);
                        validate = false;
                    } else {
                        $("#" + key).css("border-color", normal);
                    }
                } else {
                    switch ($ctrl.attr("type")) {
                        case "text":
                            if ($("#" + key).val() == "") {
                                $("#" + key).css("border-color", invalid);
                                validate = false;
                            } else {
                                $("#" + key).css("border-color", normal);
                            }
                            break;
                        case "checkbox":
                            if (value == '1') {
                                $("#" + key).css("border-color", normal);
                            } else {
                                $("#" + key).css("border-color", invalid);
                                validate = false;
                            }
                            break;
                        case "radio":
                            if ($('input:radio[name="' + key + '"][value="' + value + '"]').prop('checked') == true) {
                                $('input:radio[name="' + key + '"]').css("border-color", normal);
                            } else {
                                $('input:radio[name="' + key + '"]').css("border-color", invalid);
                                validate = false;
                            }
                            break;
                    }
                }
            }
        }
    });
    return validate;
}
         
         
function validateIdSelect2(ctrl, id, text) {
    if ($('#' + ctrl).find("option[value='" + id + "']").length) {
        $('#' + ctrl).val(id).trigger('change');
    } else {
        var newOption = new Option(text, id, true, true);
        $('#' + ctrl).append(newOption).trigger('change');
    }
}

function populateFormDetail($form, data, Entity) {

    resetForm($form);
    $.each(data, function (key, value) {
        var $ctrl = $form.find('[id=' + Entity + key + ']');
        if ($ctrl.is('select')) {
            $("#" + Entity + key).val(value);
            $("#" + Entity + key).trigger('change');
        } else if ($ctrl.is('textarea')) {
            $ctrl.val(value);
        } else {
            switch ($ctrl.attr("type")) {
                case "text":
                    $ctrl.val(value);
                    break;
                case "hidden":
                    $ctrl.val(value);
                    break;
                case "checkbox":
                    if (value == '1')
                        $ctrl.prop('checked', true);
                    else
                        $ctrl.prop('checked', false);
                    break;
            }
        }
    });
}

function handleMenu(url) {
    window.location.href = url;
}

function handleReport(url) {
    window.open(url, '_blank');
}

function readOnlyForm($form, data) {

    resetForm($form);
    $.each(data, function (key, value) {
        var $ctrl = $form.find('[id=' + key + ']');
        if ($ctrl.is('select')) {
            $("#" + key).val(value);
            $("#" + key).attr("disabled", true);
            $("#" + key).trigger('change');
        } else if ($ctrl.is('textarea')) {
            $ctrl.val(value);
        } else {
            switch ($ctrl.attr("type")) {
                case "text":
                    $ctrl.val(value);
                    $ctrl.attr('readonly', true);
                    break;
                case "hidden":
                    $ctrl.val(value);
                    $ctrl.attr('readonly', true);
                    break;
                case "checkbox":
                    if (value == '1')
                        $ctrl.prop('checked', true);
                    else
                        $ctrl.prop('checked', false);
                    break;
                    $ctrl.attr('readonly', true);
            }
        }
    });
}


function populateForm($form, data) {

    resetForm($form);
    $.each(data, function (key, value) {
        var $ctrl = $form.find('[id=' + key + ']');
        if ($ctrl.is('select')) {
            $("#" + key).val(value);
            $("#" + key).trigger('change');
        } else if ($ctrl.is('textarea')) {
            $ctrl.val(value);
        } else {
            switch ($ctrl.attr("type")) {
                case "text":
                    $ctrl.val(value);
                    break;
                case "hidden":
                    $ctrl.val(value);
                    break;
                case "checkbox":
                    if (value == '1')
                        $ctrl.prop('checked', true);
                    else
                        $ctrl.prop('checked', false);
                    break;
                case "radio":
                    $('input:radio[name="' + key + '"][value="' + value + '"]').prop('checked', true);
                    break;
            }
        }
    });
}

function resetForm($form) {
    $form.find('input:text, input:password, input:file, select, textarea').val('');
    $form.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
    $(".chosen-select").trigger("chosen:updated");
}

function resetFormExclude($form, exclude) {
    $form.find('input:text, input:password, input:file, select, textarea').not(exclude).val('');
    $form.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
    $(".chosen-select").trigger("chosen:updated");
}
