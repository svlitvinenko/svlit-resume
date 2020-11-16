package ru.svlit.feature.wall.adapter.persistence.datasource;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.svlit.architecture.annotation.PersistenceAdapter;
import ru.svlit.feature.wall.adapter.persistence.datasource.model.MessageModel;

import java.util.List;

@PersistenceAdapter
public interface MessageDataSource extends MongoRepository<MessageModel, String> {
    List<MessageModel> findByTag(String tag);
}
