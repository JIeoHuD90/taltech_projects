####

Restoring influxdb


sudo service telegraf stop
sudo influx -execute 'DROP DATABASE telegraf'
sudo influxd restore -portable -database telegraf /home/backup/restore/influxdb
sudo service telegraf start

####
Restoring mysql( on master only)

sudo -u backup duplicity --no-encryption restore rsync://LeonidVagulin@backup-server.deez.lv//home/LeonidVagulin/mysql /home/backup/restore/mysql
sudo mysql agama < /home/backup/restore/mysql/agama.sql

####
If there is a need of restoring full backups of both mysql and influx. Influxdb should be restored first.
