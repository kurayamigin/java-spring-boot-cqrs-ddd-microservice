package my.microservice.videogames.application.queries.dtos;


public interface IQuery<TKey> {
    TKey getId();
    void setId(TKey id);
}
