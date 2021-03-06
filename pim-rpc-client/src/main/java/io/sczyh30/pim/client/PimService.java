package io.sczyh30.pim.client;

import io.sczyh30.pim.entity.EntityWithDate;
import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.entity.PIMContact;
import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.entity.PIMNote;
import io.sczyh30.pim.entity.PIMTodo;
import rx.Completable;
import rx.Single;

import java.time.LocalDate;
import java.util.List;

/**
 * Asynchronous service interface for personal information manager.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 * @version v1 (CLIENT_SIDE)
 */
public interface PimService extends AutoCloseable {

  /**
   * Get all PIM note entities from the backend.
   *
   * @return asynchronous result
   */
  Single<List<PIMNote>> getNotes();

  /**
   * Get all PIM note entities from the backend by specific owner.
   *
   * @param owner specific owner
   * @return asynchronous result
   */
  Single<List<PIMNote>> getNotes(String owner);

  /**
   * Get all PIM todo entities from the backend.
   *
   * @return asynchronous result
   */
  Single<List<PIMTodo>> getTodos();

  /**
   * Get all PIM todo entities from the backend by specific owner.
   *
   * @param owner specific owner
   * @return asynchronous result
   */
  Single<List<PIMTodo>> getTodos(String owner);

  /**
   * Get all PIM appointment entities from the backend.
   *
   * @return asynchronous result
   */
  Single<List<PIMAppointment>> getAppointments();

  /**
   * Get all PIM appointment entities from the backend by specific owner.
   *
   * @param owner specific owner
   * @return asynchronous result
   */
  Single<List<PIMAppointment>> getAppointments(String owner);

  /**
   * Get all PIM contact entities from the backend.
   *
   * @return asynchronous result
   */
  Single<List<PIMContact>> getContacts();

  /**
   * Get all PIM contact entities from the backend by specific owner.
   *
   * @param owner specific owner
   * @return asynchronous result
   */
  Single<List<PIMContact>> getContacts(String owner);

  /**
   * Get all entities with specific date.
   *
   * @param date specific date
   * @return asynchronous result of the entities
   */
  Single<List<EntityWithDate>> getItemsForDate(LocalDate date);

  /**
   * Get all entities with specific date by specific owner.
   *
   * @param date  specific date
   * @param owner specific owner
   * @return asynchronous result of the entities
   */
  Single<List<EntityWithDate>> getItemsForDate(LocalDate date, String owner);

  /**
   * Get all PIM entities from the backend.
   *
   * @return asynchronous result of the entities
   */
  Single<List<PIMEntity>> getAll();

  /**
   * Get all PIM entities from the backend by specific owner.
   *
   * @param owner specific owner
   * @return asynchronous result of the entities
   */
  Single<List<PIMEntity>> getAllByOwner(String owner);

  /**
   * Put a PIM entity into the backend.
   *
   * @param entity entity object
   * @return asynchronous result indicating the status of the add operation
   */
  Completable add(PIMEntity entity);

  /**
   * Update an existing PIM entity by ID.
   *
   * @param id     entity ID
   * @param entity entity object
   * @return asynchronous result indicating the status of the update operation
   */
  Completable update(String id, PIMEntity entity);

  /**
   * Remove a PIM entity from the backend.
   *
   * @param id entity id
   * @return asynchronous result indicating the status of the remove operation
   */
  Completable remove(String id);

  /**
   * Extending the {@link AutoCloseable} interface.
   */
  void close();
}
