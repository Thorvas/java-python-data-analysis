from functions import *
from powiat import Powiat
import requests
import json
import time
import numpy as np
import psycopg2

conn = psycopg2.connect(
    database="local_analysis",
    user='postgres',
    password='master',
    host='localhost',
    port='5432'
)

cursor = conn.cursor()

powiat = [] #list of powiat items
id = 1 #id needed for powiat class
r_squared = []

while True:
    # Because of API's system, I need to reconstruct a name of a powiat and exclude another one
    for i in range(get_total_powiats()):
        if get_powiat_name(i) != 'Powiat m. Wałbrzych do 2002' and get_powiat_name(i) != "Powiat warszawski":
            if get_powiat_name(i) != 'Powiat m. Wałbrzych od 2013':
                item = Powiat(id, get_powiat_name(i), get_powiat_voivodeship(i), get_powiat_population(i), get_powiat_growth(i), 0, get_current_date())
                id += 1
            else:
                item = Powiat(id, 'Powiat m. Wałbrzych', get_powiat_voivodeship(i), get_powiat_population(i), get_powiat_growth(i), 0, get_current_date())
                id += 1
            powiat.append(item)

    # Predicting population value for next year for each powiat
    for item in powiat:
        prediction, r_value = item.predict_population()
        item.prediction = prediction
        r_squared.append(r_value)


    for i in range(len(powiat)):
        # print(f'{powiat[i].name}, {type(powiat[i].name)}\n {powiat[i].voivodeship}, {type(powiat[i].voivodeship)}\n{powiat[i].population}, {type(powiat[i].population)}\n{powiat[i].last_updated}, {type(powiat[i].last_updated)}')
        pop = powiat[i].population

        db_id = powiat[i].id
        db_name = powiat[i].name
        db_voivodeship = powiat[i].voivodeship
        db_population = pop[list(pop.keys())[-1]]
        db_prediction = powiat[i].prediction
        db_last_updated = powiat[i].last_updated

        cursor.execute("INSERT INTO public.powiat(id, name, voivodeship, population, prediction, last_updated) VALUES (%s, %s, %s, %s, %s, %s);", (db_id, db_name, db_voivodeship, db_population, db_prediction, db_last_updated))

        print(f'Succesfully inserted record with id {db_id}')

    conn.commit()
    time.sleep(60)

    #Table is cleared for updated data. The code itself is cleared to avoid conflicts
    cursor.execute("TRUNCATE powiat;")
    powiat = []
    id = 1
    r_squared = []

conn.close()


# for i in range(len(powiat)):
#     payload = powiat[i].asdict()
#     payload = json.dumps(payload, ensure_ascii=False) #ensure_ascii=False makes it possible for json to contain Polish characters
#     print(f'Posting powiat number {i + 1}\nPosting {powiat[i].name}')
#     posted_powiat = requests.post('http://localhost:8080/api/postEstimation', json=payload)
#     print(f'Status code: {posted_powiat.status_code}')

# Testing the results:
# for i in range(len(powiat)):
#     print(f'Name: {powiat[i].name}, {powiat[i].population["2022"]}->{powiat[i].prediction}, R: {r_squared[i]}')

# print(np.mean(r_squared))
