package ru.svlit.feature.sweater.adapter.persistence.datasource;

import org.springframework.data.repository.CrudRepository;
import ru.svlit.architecture.annotation.PersistenceAdapter;
import ru.svlit.feature.sweater.adapter.persistence.datasource.model.MessageModel;

import java.util.List;

@PersistenceAdapter
public interface MessageDataSource extends CrudRepository<MessageModel, Integer> {
    List<MessageModel> findByTag(String tag);
}
