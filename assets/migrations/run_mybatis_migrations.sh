#!/bin/bash
curl -L  "https://github.com/mybatis/migrations/releases/download/mybatis-migrations-3.3.1/mybatis-migrations-3.3.1-bundle.zip" > /tmp/mybatis-migrations-3.3.1-bundle.zip
mkdir -p /u01/opensrp/core
mkdir  /u01/opensrp/error
mkdir  /u01/opensrp/schedule
mkdir  /u01/opensrp/feed
mkdir  /u01/opensrp/form
chown -R opensrp /u01/opensrp
unzip /tmp/mybatis-migrations-3.3.1-bundle.zip -d /opt/mybatis
export MIGRATIONS_HOME=/opt/mybatis/mybatis-migrations-3.3.1
MIGRATIONS=$MIGRATIONS_HOME/bin
export PATH=$MIGRATIONS:$PATH
migrate  up --path=assets/migrations  --env=development --force