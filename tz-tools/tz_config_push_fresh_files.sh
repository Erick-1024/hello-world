#!/bin/bash

LOCAL_REPO="$HOME/git/tz-config"
GIT_DIR_ARGS="--git-dir=$LOCAL_REPO/.git --work-tree=$LOCAL_REPO"
LOG="$HOME/logs/tz_config_push.log"
TOOL="$HOME/git/tz-tools/zk_pusher/push.py"

cd $LOCAL_REPO

old_commit=$(git rev-list HEAD^1..HEAD 2>&1)

git pull

new_commit=$(git rev-list HEAD^1..HEAD)

if [ "$old_commit" != "$new_commit" ]; then
    files=$(git log --name-only ${old_commit}..${new_commit} | grep -v -E "^(commit|Author:|Date:|Merge:|\s)" | grep -v ^$)
    push_targets=$(echo "$files" | sort | uniq | xargs)

    echo ">>>>> [$(date)] push changes: ${old_commit}..${new_commit}" >> $LOG
    /usr/bin/python2 $TOOL $push_targets >> $LOG 2>&1
    echo "<<<<< [$(date)] push finished" >> $LOG
fi

# crontab
# * * * * *       flock -xn /root/lock/tz_config_refresh.lock -c '/root/git/tz-tools/tz_config_push_fresh_files.sh'
# * * * * *       sleep 15; flock -xn /root/lock/tz_config_refresh.lock -c '/root/git/tz-tools/tz_config_push_fresh_files.sh'
# * * * * *       sleep 30; flock -xn /root/lock/tz_config_refresh.lock -c '/root/git/tz-tools/tz_config_push_fresh_files.sh'
# * * * * *       sleep 45; flock -xn /root/lock/tz_config_refresh.lock -c '/root/git/tz-tools/tz_config_push_fresh_files.sh'
