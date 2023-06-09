from functions import *
from powiat import Powiat
import requests

powiat = []

# Because of API's system, I need to reconstruct a name od a powiat and exclude another one
for i in range(get_total_powiats()):
    if get_powiat_name(i) != 'Powiat m. Wałbrzych do 2002':
        if get_powiat_name(i) != 'Powiat m. Wałbrzych od 2013':
            item = Powiat(get_powiat_name(i), get_powiat_population(i), 0, get_current_date())
        else:
            item = Powiat('Powiat m. Wałbrzych', get_powiat_population(i), 0, get_current_date)
        powiat.append(item)

for i in range(get_total_powiats()):
    requests.post('localhost:8080/api/postEstimation', json=powiat[i].asdict())


