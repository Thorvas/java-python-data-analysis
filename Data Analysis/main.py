from functions import *
from powiat import Powiat
import requests
import json
import time

powiat = []

# Because of API's system, I need to reconstruct a name od a powiat and exclude another one
for i in range(get_total_powiats()):
    if get_powiat_name(i) != 'Powiat m. Wałbrzych do 2002':
        if get_powiat_name(i) != 'Powiat m. Wałbrzych od 2013':
            item = Powiat(get_powiat_name(i), get_powiat_population(i), 0, get_current_date())
        else:
            item = Powiat('Powiat m. Wałbrzych', get_powiat_population(i), 0, get_current_date())
        powiat.append(item)

for i in range(len(powiat)):
    payload = powiat[i].asdict()
    payload = json.dumps(payload, ensure_ascii=False)
    print(f'Posting powiat number {i}\nPosting {powiat[i].name}')
    posted_powiat = requests.post('https://reqres.in/api/users', json=payload)
    print(f'Status code: {posted_powiat.status_code}')