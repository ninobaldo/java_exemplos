#!/bin/bash
#-------------------------------------------------------------#
#                      Script backup 			      #
#-------------------------------------------------------------#
#
# Autor: Álvaro Rios (ninobaldo)
# Versão: 1
# Data: Dom Jul 15 11:25:24 BRT 2012
# Descrição: Script de backup. 
# Obs. para próxima versão: montar partições automaticamente, solicitar caminho do disco de backup. 

DT=$(echo "$(date +%F_%T)" | sed "s/:/\./g");
LOG=backup-$DT.log;
FILE=backup-$DT.tgz;

echo "$(date): Backup Iniciado..." > $LOG;

RESPOSTA=$'SIM\nNão'
PERGUNTA='Atenção antes de rodar certifique-se que os dados a serem copiados foram editados e customizados para o sistema atual e que esteja no diretório que o backup deverá ser salvo. Deseja continuar o backup?';

echo -e "\\033[1;39m [ \\033[1;31m$PERGUNTA\\033[1;39m ]\\033[1;0m";

select x in $RESPOSTA; do
	if [ "$x" != "" ]
	then
		if [ "$x" = "SIM" ]
		then
			break;
		else
			exit 1;
		fi;
	fi;
done

echo "$(date): Permissão para continuar data. Iniciando cópia de arquivos" >> $LOG;

echo "Arquivos copiados" >> $LOG;

/usr/bin/time -p -a -o $LOG tar -zvuf /media/Backup/backup-2012-07-16_06.21.00.tgz /media/sdb1 >> $LOG;

echo "$(date): fim do backup" >> $LOG;

#shutdown -h 0;
