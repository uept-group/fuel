#!/bin/bash

nohup java -cp ../config -Dspring.web.resources.static-locations=../static - Dserver.port=9050 -jar fuel-admin-1.8.jar &