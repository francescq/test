package com.francescq.test.database.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.francescq.test.pojo.Token;

public class TokenMapper implements ResultSetMapper<Token> {

    public TokenMapper() {
    }

    public Token map(int index, ResultSet r, StatementContext ctx) throws SQLException {
	Token token = new Token(r.getString(Token.ID), r.getString(Token.TYPE), r.getString(Token.CONTENT), r.getTimestamp(Token.DATE_EXPIRY));
	return token;
    }
}