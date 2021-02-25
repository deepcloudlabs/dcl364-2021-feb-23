class ImdbViewModel {
    constructor(){
    	this.genres = ko.observableArray([]);
    	this.genre = ko.observable("Drama");
    	this.fromYear = ko.observable(1970);
    	this.toYear = ko.observable(1979);
    	this.movies = ko.observableArray([]);
    }
    
    init() {
       //TODO: rest call to http://localhost:7100/imdb-api/api/v1/movies/genres
       fetch("http://localhost:7100/imdb-api/api/v1/movies/genres")
	       .then( res => res.json())
	       .then( genres => {genres.sort((g1,g2)=>g1.name.localeCompare(g2.name));this.genres(genres);});
    }
    
    search(){
       fetch("http://localhost:7100/imdb-api/api/v1/movies?"+
                   "genre="+this.genre() +
                   "&fromYear="+this.fromYear() +
                   "&toYear="+this.toYear() 
          ).then( res => res.json())
	       .then( movies => this.movies(movies));
    }
}

let model = new ImdbViewModel();
window.onload = () => {
    ko.applyBindings(model);
    model.init();
}