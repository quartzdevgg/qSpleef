package com.QarthO.Spleef.Commands;

import com.QarthO.Spleef.utils.Language;

public class CommandNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommandNotFoundException(final String cmd) {
		super(Language.ERROR_UKNOWN_COMMAND.getMessage());
	}
	
	public CommandNotFoundException(final String cmd, final Throwable throwable) {
        super(Language.ERROR_UKNOWN_COMMAND.getMessage(), throwable);
    }
}
