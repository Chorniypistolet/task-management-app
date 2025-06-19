package com.portfolioapp.taskamanagementapp.dropbox;

@FunctionalInterface
public interface DropboxActionResolver<T> {
    T perform() throws Exception;
}
