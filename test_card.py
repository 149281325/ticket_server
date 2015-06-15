import sys, requests, json, time, random

###########################################################
# Change Test Configureation here: 
###########################################################
req_interval = 0

add_card_test_data = 'add_card.txt'

check_card_test_data ='check_card.txt'

add_card_url  =  'http://localhost:7001/TicketServer/webresources/card'

check_card_url = 'http://localhost:7001/TicketServer/webresources/card'

# accept_h  = {'Accept': 'application/json'}
content_h = {'Content-Type': 'application/json'}


###########################################################
# Functions
###########################################################
def test_add_cards():
    all_lines = []
    f = open(add_card_test_data, "r")
    try:
        all_lines = f.readlines()
    finally:
        f.close()
    for payload in all_lines:
        print '--> ', payload
        r = requests.put(add_card_url, data=payload, headers=content_h)
        print '<-- ', r.text
        req_interval and time.sleep(req_interval)
    
        
def test_check_cards():
    all_lines = []
    f = open(check_card_test_data, "r")
    try:
        all_lines = f.readlines()
    finally:
        f.close()
    random.shuffle(all_lines)
    for payload in all_lines:
        print '--> ', payload
        r = requests.post(check_card_url, data=payload, headers=content_h)
        print '<-- ', r.text
        req_interval and time.sleep(req_interval)
        
def usage():
    print '"python test_card.py add" or "python test_card.py check"'
    print 'Run "python test_card.py sample" for an example'

def sample():
    payload='{"type":"add","uid":"FFFFFFFF","start_time":"20150608","end_time":"20951231","count":5}'
    print '--> ', payload
    r = requests.put(add_card_url, data=payload, headers=content_h)
    print '<-- ', r.text
    
    time.sleep(3)
    payload='{"type":"check","uid":"FFFFFFFF","count":1,"valid_check":true}'
    print '--> ', payload
    r = requests.post(check_card_url, data=payload, headers=content_h)
    print '<-- ', r.text

###########################################################
# Main Process
###########################################################
if len(sys.argv) != 2:
    usage()
elif sys.argv[1] == 'add':
    test_add_cards()
elif sys.argv[1] == 'check':
    test_check_cards()
elif sys.argv[1] == 'sample':
    sample()
else:
    usage()
