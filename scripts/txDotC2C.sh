#!/bin/sh

exec java $* -cp "`dirname $0`/lib/*" -Dconfig.file=`dirname $0`/prod.conf -Dhttp.port=8088 play.core.server.NettyServer `dirname $0`