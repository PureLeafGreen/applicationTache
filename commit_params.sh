#!/bin/bash

echo "Message pour le commit : "
read message
git add ./$1
git commit -m "$message"
git push
