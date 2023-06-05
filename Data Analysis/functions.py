import requests

api_url = 'https://bdl.stat.gov.pl/api/v1/data/by-variable/72305?unit-level=2&aggregate-id=1&page=0&page-size=16&lang=pl&format=json'

response = requests.get(api_url)
response = response.json()

def fetch_api_population():
    '''Fetches number of people per voivodeship from BDL GUS API.'''
    return response['results']

def get_voivodeship_name(voivodeship_id):
    '''Returns name of given voivodeship'''
    voivodeship_name = response['results'][voivodeship_id]['name'].capitalize()

    return voivodeship_name

def get_voivodeship_population(voidoveship_id):
    '''Returns population of given voivodeship'''
    voivodeship_population = dict()
    for item in response['results'][voidoveship_id]['values']:
        voivodeship_population[item['year']] = item['val']

    return voivodeship_population



