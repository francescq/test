package com.francescq.test.database.dao;

import java.sql.Timestamp;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.francescq.test.database.dao.mapper.TokenMapper;
import com.francescq.test.pojo.Token;

@RegisterMapper(TokenMapper.class)
public interface TokenDao {

    @SqlUpdate("create table token (id int primary key, type varchar(20), content varchar(2000), date_expiry TIMESTAMP )")
    public void createSomethingTable();

    @SqlUpdate("insert into token (id, type, content, date_expiry) values (:id, :type, :content, :date_expiry)")
    public void insert(@Bind(Token.ID) int id, @Bind(Token.TYPE) String type, @Bind(Token.CONTENT) String content, @Bind(Token.DATE_EXPIRY) Timestamp dateExpiry);

    @SqlQuery("select id,type,content,date_expiry from token t where t.id = :id")
    public Token getById(@Bind(Token.ID) int id);

    /**
     * close with no args is used to close the connection
     */
    void close();
}