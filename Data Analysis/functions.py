import requests
import datetime

# Because of API's limit to fetch at most 100 results per page, I have to make foir different requests.
api_url_page0 = 'https://bdl.stat.gov.pl/api/v1/data/by-variable/72305?unit-level=5&aggregate-id=1&page=0&page-size=100&lang=pl&format=json'
api_url_page1 = 'https://bdl.stat.gov.pl/api/v1/data/by-variable/72305?unit-level=5&aggregate-id=1&page=1&page-size=100&lang=pl&format=json'
api_url_page2 = 'https://bdl.stat.gov.pl/api/v1/data/by-variable/72305?unit-level=5&aggregate-id=1&page=2&page-size=100&lang=pl&format=json'
api_url_page3 = 'https://bdl.stat.gov.pl/api/v1/data/by-variable/72305?unit-level=5&aggregate-id=1&page=3&page-size=100&lang=pl&format=json'

response_page0 = requests.get(api_url_page0)
response_page1 = requests.get(api_url_page1)
response_page2 = requests.get(api_url_page2)
response_page3 = requests.get(api_url_page3)

response_page0 = response_page0.json()
response_page1 = response_page1.json()
response_page2 = response_page2.json()
response_page3 = response_page3.json()

# Merging multile responses into one
response = response_page0['results'] + response_page1['results'] + response_page2['results'] + response_page3['results']


def fetch_api_population():
    '''Fetches number of people per powiat from BDL GUS API.'''
    return response

def get_powiat_name(powiat_id):
    '''Returns name of given powiat'''
    powiat_name = response[powiat_id]['name']

    return powiat_name

def get_powiat_population(powiat_id):
    '''Returns population of given powiat'''
    powiat_population = dict()

    for item in response[powiat_id]['values']:
        if int(item['year']) >= 2013:
            powiat_population[item['year']] = item['val']

    return powiat_population

def get_total_powiats():
    '''Returns total number of powiats'''
    return len(response)

def get_current_date():
    '''Returns current date in format YYYY-mm-dd'''
    date = datetime.datetime.now()
    date = date.strftime('%Y-%m-%d')

    return date