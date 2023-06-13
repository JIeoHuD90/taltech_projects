####
Restoring mysql

duplicity --no-encryption restore rsync://LeonidVagulin@backup-server.deez.lv//home/LeonidVagulin/ /home/backup/restore/
mysql agama < /home/backup/restore/agama.sql

Restoring influxdb

service telegraf stop
influx -execute 'DROP DATABASE telegraf'
influxd restore -portable -database telegraf /home/backup/influxdb
####
If needed full restore then restore influxdb first , second mysql
