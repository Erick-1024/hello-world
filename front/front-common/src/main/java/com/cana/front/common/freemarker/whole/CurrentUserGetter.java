package com.cana.front.common.freemarker.whole;

import java.util.List;

import com.cana.member.authorization.common.SecurityContextUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 *
 * @since Nov 11, 20151:53:53 PM
 * @author dev1
 *
 */
public class CurrentUserGetter implements TemplateMethodModelEx {

    @SuppressWarnings("rawtypes")
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return SecurityContextUtils.getUserFromSession();
    }

}
