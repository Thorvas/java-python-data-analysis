class Powiat:
    def __init__(self, id, name, voivodeship, population, estimation, last_updated):
        self.id = id
        self.name = name
        self.voivodeship = voivodeship
        self.population = population
        self.estimation = estimation
        self.last_updated = last_updated


    def asdict(self):
        return{
                'id': self.id,
                'name': self.name, 
                'voivodeship': self.voivodeship,
                'population': self.population,
                'estimation': self.estimation, 
                'last_updated': self.last_updated
                }