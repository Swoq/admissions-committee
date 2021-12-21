package com.swoqe.admissionscommittee.controller;

import com.swoqe.admissionscommittee.page.PageLink;
import com.swoqe.admissionscommittee.page.SortOrder;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public abstract class BaseController {

    protected void checkNotBlank(String name, String param) {
        if (StringUtils.isBlank(param)) {
            throw new IllegalArgumentException("Parameter '" + name + "' can't be empty!");
        }
    }

    protected void checkArrayParameter(String name, String[] params) {
        if (params == null || params.length == 0) {
            throw new IllegalArgumentException("Parameter '" + name + "' can't be empty!");
        } else {
            for (String param : params) {
                checkNotBlank(name, param);
            }
        }
    }

    protected PageLink createPageLink(int pageSize, int page, String textSearch, String sortProperty, String sortOrder) {
        if (!StringUtils.isEmpty(sortProperty)) {
            SortOrder.Direction direction = SortOrder.Direction.ASC;
            if (!StringUtils.isEmpty(sortOrder)) {
                try {
                    direction = SortOrder.Direction.valueOf(sortOrder.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Unsupported sort order '" + sortOrder + "'! Only 'ASC' or 'DESC' types are allowed.");
                }
            }
            SortOrder sort = new SortOrder(sortProperty, direction);
            return new PageLink(pageSize, page, textSearch, sort);
        } else {
            return new PageLink(pageSize, page, textSearch);
        }
    }

    protected UUID toUUID(String id) {
        return UUID.fromString(id);
    }
}
