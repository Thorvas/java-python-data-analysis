from functions import *
from powiat import Powiat

powiats = []

for i in range(382):
    powiat = Powiat(get_powiat_name(i), get_powiat_population(i))
    powiats.append(powiat)
