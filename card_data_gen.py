import random

###########################################################
# Use this script to generate test data
###########################################################
remain_times_card_count = 1990
remain_times_card_count_n = 10

balance_card_count = 1990
balance_card_count_n = 10

remain_times_date_card_count = 1990
remain_times_date_card_count_n = 10

balance_date_card_count = 990
balance_date_card_count_n = 10

balance_nodate_valid_card_count = 990
balance_nodate_valid_card_count_n = 10

valid_date_start = '20150608'
valid_date_end   = '20151231'

all_cards = list()
all_checks = list()

# TODO: change me to be more real-world
uid_index = -1
def gen_uid():
    global uid_index
    uid_index += 1
    return hex(uid_index)[2:]
    

def gen_cards_remian_times(count, negative=False):
    for i in range(count):
        points = random.randint(1,10)
        uid = gen_uid()
        data = '{"type":"add","uid":"%s","count":%d}' % (uid, points)
        all_cards.append(data)
        minus = 1
        if negative:
            minus = points + 1
        check = '{"type":"check","uid":"%s","count":%d}' % (uid, minus)
        all_checks.append(check)
        
def gen_cards_balance(count, negative=False):
    for i in range(count):
        amount = random.uniform(100,300)
        uid = gen_uid()
        data = '{"type":"add","uid":"%s","balance":%.2f}' % (uid, amount)
        all_cards.append(data)
        minus = 100.00
        if negative:
            minus = amount + 10.00
        check = '{"type":"check","uid":"%s","balance":%.2f}' % (uid, minus)
        all_checks.append(check)
        
def gen_cards_remian_times_date(count, negative=False):
    for i in range(count):
        points = random.randint(1,10)
        uid = gen_uid()
        data = '{"type":"add","uid":"%s","start_time":"%s","end_time":"%s","count":%d}' % (uid, valid_date_start, valid_date_end, points)
        all_cards.append(data)
        minus = 1
        if negative:
            minus = points + 1
        check = '{"type":"check","uid":"%s","count":%d,"valid_check":true}' % (uid, minus)
        all_checks.append(check)

def gen_cards_balance_date(count, negative=False):
    for i in range(count):
        amount = random.uniform(100,300)
        uid = gen_uid()
        data = '{"type":"add","uid":"%s","start_time":"%s","end_time":"%s","balance":%.2f}' % (uid, valid_date_start, valid_date_end, amount)
        if negative:
            data = '{"type":"add","uid":"%s","start_time":"19930923","end_time":"19931015","balance":%.2f}' % (uid, amount)
        all_cards.append(data)
        minus = 100.00
        check = '{"type":"check","uid":"%s","balance":%.2f,"valid_check":true}' % (uid, minus)
        all_checks.append(check)
        
def gen_cards_balance_nodate_valid(count, negative=False):
    for i in range(count):
        amount = random.uniform(100,300)
        uid = gen_uid()
        data = '{"type":"add","uid":"%s","balance":%.2f}' % (uid, amount)
        all_cards.append(data)
        minus = 100.00
        check = '{"type":"check","uid":"%s","balance":%.2f,"valid_check":true,"nodate_valid":true}' % (uid, minus)
        if negative:
            check = '{"type":"check","uid":"%s","balance":%.2f,"valid_check":true}' % (uid, minus)
        all_checks.append(check)
        
print 'go'
gen_cards_remian_times(remain_times_card_count)
gen_cards_remian_times(remain_times_card_count_n, True)

gen_cards_balance(balance_card_count)
gen_cards_balance(balance_card_count_n, True)

gen_cards_remian_times_date(remain_times_date_card_count)
gen_cards_remian_times_date(remain_times_date_card_count_n, True)

gen_cards_balance_date(balance_date_card_count)
gen_cards_balance_date(balance_date_card_count_n, True)

gen_cards_balance_nodate_valid(balance_nodate_valid_card_count)
gen_cards_balance_nodate_valid(balance_nodate_valid_card_count_n, True)

sep = '\n'
f1=open('add_card.txt', 'w')
f1.write(sep.join(all_cards))
f1.close()
f2=open('check_card.txt', 'w')
f2.write(sep.join(all_checks))
f2.close()
