package com.uca.spring.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateTag extends SimpleTagSupport {

    private String id;
    private String name;
    private String value;
    private String tabindex;
    private String required;

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder scriptBuilder = new StringBuilder();
        try {

            scriptBuilder.append(" <input type=\"text\" class=\"form-control\" ");

            if (id != null && !id.trim().equals("")) {
                scriptBuilder.append("id=\"").append(id).append("\" ");
            }

            if (name != null && !name.trim().equals("")) {
                scriptBuilder.append(" name=\"").append(name).append("\"");
            }

            if (value != null && !value.trim().equals("")) {
                scriptBuilder.append(" value=\"").append(value).append("\"");
            }

            if (required != null && required.trim().equals("true")) {
                scriptBuilder.append(" required=\"").append(required).append("\" ");
            }

            if (tabindex != null && !tabindex.trim().equals("")) {
                scriptBuilder.append(" tabindex=\"").append(tabindex).append("\" ");
            }

            scriptBuilder.append(" placeholder=\"Seleccione una fecha\" data-mask=\"99/99/9999\" data-dateformat=\"dd/mm/yyyy\">\n");
            scriptBuilder.append("  <span class=\"input-group-addon\"><i class=\"fa fa-calendar\"></i></span>\n");
            scriptBuilder.append("  <script>");
            scriptBuilder.append("  $(document).ready(function () {\n");

            scriptBuilder.append("      $(\"#").append(id).append("\").datepicker({dateFormat: \"dd/mm/yy\"})\n");
            scriptBuilder.append("          .on('change.dp', function (e) {\n");
            scriptBuilder.append("$($(this)[0].form).bootstrapValidator('revalidateField', '").append(name).append("');\n");
            scriptBuilder.append("      });");
            scriptBuilder.append("  });");
            scriptBuilder.append("  </script>");
            getJspContext().getOut().write(scriptBuilder.toString());

        } catch (Exception ex) {
            getJspContext().getOut().write("error textbox");
        }
    }
}
