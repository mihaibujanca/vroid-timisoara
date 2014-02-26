#!/bin/bash
echo "$1 $2 $3"
device="/dev/input/event"
if [[ "$1" == "0" ]]; then
	sudo ./create
fi
if [[ "$1" == "1" ]]; then
	echo $device$(dmesg | grep VROID | sed 's/.*input\([0-9]*\).*/\1/' | head -n 1)
	sudo ./driver "$2" "$3" "$device$(dmesg | grep VROID | sed 's/.*input\([0-9]*\).*/\1/' | head -n 1)"
fi
if [[ "$1" == "2" ]]; then
	sudo ./destroy
fi

