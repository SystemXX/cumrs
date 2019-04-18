package com.bxait.cumrs.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
public class MySqlConfig extends MySQL5InnoDBDialect  {
        @Override
        public String getTableTypeString() {
            return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
        }
    }
