package com.uca.spring.util;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextBoxTag extends SimpleTagSupport {

    private String id;
    private String name;
    private String tabindex;
    private String required;
    private String minlength;
    private String maxlength;
    private String readonly;
    private String value;
    private String placeholder;

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder scriptBuilder = new StringBuilder();
        try {
            scriptBuilder.append(" <input type=\"text\" autocomplete=\"off\" ");

            if (id != null && !id.trim().equals("")) {
                scriptBuilder.append("id=\"").append(id).append("\" ");
            }
            
             if (placeholder != null && !placeholder.trim().equals("")) {
                scriptBuilder.append("placeholder=\"").append(placeholder).append("\" ");
            }

            if (name != null && !name.trim().equals("")) {
                scriptBuilder.append(" name=\"").append(name).append("\"");
            }

            if (required != null && required.trim().equals("true")) {
                scriptBuilder.append(" required=\"").append(required).append("\" ");
            }

            if (minlength != null && !minlength.trim().equals("")) {
                scriptBuilder.append(" minlength=\"").append(minlength).append("\" ");
            }

            if (maxlength != null && !maxlength.trim().equals("")) {
                scriptBuilder.append(" maxlength\"").append(maxlength).append("\" ");
            }

            if (tabindex != null && !tabindex.trim().equals("")) {
                scriptBuilder.append(" tabindex=\"").append(tabindex).append("\" ");
            }

            if (readonly != null && readonly.trim().equals("true")) {
                scriptBuilder.append(" readonly=\"true\" ");
            }
            
            if (value != null && !value.trim().equals("")) {
                scriptBuilder.append(" value=\"").append(value).append("\"");
            }

            scriptBuilder.append(" class=\"form-control\" />\n");
            scriptBuilder.append("<span class=\"input-group-addon\"><i class=\"fa fa-text-width\"></i></span> \n");
            getJspContext().getOut().write(scriptBuilder.toString());

        } catch (Exception ex) {
          
            getJspContext().getOut().write("error textbox");
        }
    }
}
