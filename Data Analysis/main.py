from functions import *
from powiat import Powiat
import requests
import json
import time

powiat = [] #list of powiat items
id = 1 #id needed for powiat class

# Because of API's system, I need to reconstruct a name od a powiat and exclude another one
for i in range(get_total_powiats()):
    if get_powiat_name(i) != 'Powiat m. Wałbrzych do 2002' and get_powiat_name(i) != "Powiat warszawski":
        if get_powiat_name(i) != 'Powiat m. Wałbrzych od 2013':
            item = Powiat(id, get_powiat_name(i), get_powiat_voivodeship(i), get_powiat_population(i), 0, get_current_date())
            id += 1
        else:
            item = Powiat(id, 'Powiat m. Wałbrzych', get_powiat_voivodeship(i), get_powiat_population(i), 0, get_current_date())
            id += 1
        powiat.append(item)

for i in range(len(powiat)):
    payload = powiat[i].asdict()
    payload = json.dumps(payload, ensure_ascii=False) #ensure_ascii=False makes it possible for json to contain Polish characters
    print(f'Posting powiat number {i + 1}\nPosting {powiat[i].name}')
    posted_powiat = requests.post('http://localhost:8080/api/postEstimation', json=payload)
    print(f'Status code: {posted_powiat.status_code}')