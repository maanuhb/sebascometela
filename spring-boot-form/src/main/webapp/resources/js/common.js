
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
         