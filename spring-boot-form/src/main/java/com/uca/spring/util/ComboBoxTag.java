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
public class ComboBoxTag extends SimpleTagSupport {

    private String id;
    private String url;
    private String name;
    private String tabindex;
    private String required;
    private String readonly;
    private String placeholder;
    private String value;

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder scriptBuilder = new StringBuilder();
        try {
            scriptBuilder.append(" <select ");

            if (id != null && !id.trim().equals("")) {
                scriptBuilder.append("id=\"").append(id).append("\" ");
            }

            if (name != null && !name.trim().equals("")) {
                scriptBuilder.append(" name=\"").append(name).append("\"");
            }

            if (required != null && required.trim().equals("true")) {
                scriptBuilder.append(" required=\"").append(required).append("\" ");
            }

            if (placeholder != null && !placeholder.trim().equals("")) {
                scriptBuilder.append(" data-placeholder=\"").append(placeholder).append("\" ");
            }

            if (tabindex != null && !tabindex.trim().equals("")) {
                scriptBuilder.append(" tabindex=\"").append(tabindex).append("\" ");
            }

            if (readonly != null && readonly.trim().equals("true")) {
                scriptBuilder.append(" readonly=\"true\" ");
            }

            scriptBuilder.append(" class=\"form-control \"  style=\"width: 100%;\" >\n");
            //scriptBuilder.append("<option value=\"\"></option>\n");
            scriptBuilder.append(" </select> \n");
            scriptBuilder.append("<script>\n");

            scriptBuilder.append("  $(document).ready(function () {\n");
            scriptBuilder.append("      $('#").append(id).append("').select2({ allowClear: false});\n");
            scriptBuilder.append("      $.getJSON(\"").append(url).append("\", function (result) {\n");
            scriptBuilder.append("          $.each(result, function () {\n");
            scriptBuilder.append("              $(\"#").append(id).append("\").append(new Option(this.description, this.value));\n");
            scriptBuilder.append("          });\n");
            scriptBuilder.append("      $(\"#").append(id).append("\").val(\"").append(value).append("\").trigger('change');\n");
            scriptBuilder.append("      });\n");

            scriptBuilder.append("  });\n");

            scriptBuilder.append("</script>\n");

            getJspContext().getOut().write(scriptBuilder.toString());

        } catch (Exception ex) {
            
            getJspContext().getOut().write("error combobox");
        }
    }
}
