
@file:Suppress(
  "KotlinRedundantDiagnosticSuppress",
  "LocalVariableName",
  "MayBeConstant",
  "RedundantVisibilityModifier",
  "RemoveEmptyClassBody",
  "SpellCheckingInspection",
  "LocalVariableName",
  "unused",
)

package connectors.default

import com.google.firebase.FirebaseApp
import com.google.firebase.dataconnect.ConnectorConfig
import com.google.firebase.dataconnect.DataConnectSettings
import com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
import com.google.firebase.dataconnect.FirebaseDataConnect
import com.google.firebase.dataconnect.generated.GeneratedConnector
import com.google.firebase.dataconnect.generated.GeneratedMutation
import com.google.firebase.dataconnect.generated.GeneratedOperation
import com.google.firebase.dataconnect.generated.GeneratedQuery
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import java.util.Objects
import java.util.WeakHashMap
import com.google.firebase.dataconnect.getInstance as _fdcGetInstance

public interface DefaultConnector : GeneratedConnector<DefaultConnector> {
  override val dataConnect: FirebaseDataConnect

  
    public val createCity: CreateCityMutation
  
    public val createCourse: CreateCourseMutation
  
    public val createCourseGroup: CreateCourseGroupMutation
  
    public val createStudent: CreateStudentMutation
  
    public val deleteCourseGroup: DeleteCourseGroupMutation
  
    public val listAllCourseGroups: ListAllCourseGroupsQuery
  

  public companion object {
    @Suppress("MemberVisibilityCanBePrivate")
    public val config: ConnectorConfig = ConnectorConfig(
      connector = "default",
      location = "europe-southwest1",
      serviceId = "eschool-696bb-service",
    )

    public fun getInstance(
      dataConnect: FirebaseDataConnect
    ):DefaultConnector = synchronized(instances) {
      instances.getOrPut(dataConnect) {
        DefaultConnectorImpl(dataConnect)
      }
    }

    private val instances = WeakHashMap<FirebaseDataConnect, DefaultConnectorImpl>()
  }
}

public val DefaultConnector.Companion.instance:DefaultConnector
  get() = getInstance(FirebaseDataConnect._fdcGetInstance(config))

public fun DefaultConnector.Companion.getInstance(
  settings: DataConnectSettings = DataConnectSettings()
):DefaultConnector =
  getInstance(FirebaseDataConnect._fdcGetInstance(config, settings))

public fun DefaultConnector.Companion.getInstance(
  app: FirebaseApp,
  settings: DataConnectSettings = DataConnectSettings()
):DefaultConnector =
  getInstance(FirebaseDataConnect._fdcGetInstance(app, config, settings))

private class DefaultConnectorImpl(
  override val dataConnect: FirebaseDataConnect
) : DefaultConnector {
  
    override val createCity by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateCityMutationImpl(this)
    }
  
    override val createCourse by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateCourseMutationImpl(this)
    }
  
    override val createCourseGroup by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateCourseGroupMutationImpl(this)
    }
  
    override val createStudent by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateStudentMutationImpl(this)
    }
  
    override val deleteCourseGroup by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteCourseGroupMutationImpl(this)
    }
  
    override val listAllCourseGroups by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListAllCourseGroupsQueryImpl(this)
    }
  

  @ExperimentalFirebaseDataConnect
  override fun operations(): List<GeneratedOperation<DefaultConnector, *, *>> =
    queries() + mutations()

  @ExperimentalFirebaseDataConnect
  override fun mutations(): List<GeneratedMutation<DefaultConnector, *, *>> =
    listOf(
      createCity,
        createCourse,
        createCourseGroup,
        createStudent,
        deleteCourseGroup,
        
    )

  @ExperimentalFirebaseDataConnect
  override fun queries(): List<GeneratedQuery<DefaultConnector, *, *>> =
    listOf(
      listAllCourseGroups,
        
    )

  @ExperimentalFirebaseDataConnect
  override fun copy(dataConnect: FirebaseDataConnect) =
    DefaultConnectorImpl(dataConnect)

  override fun equals(other: Any?): Boolean =
    other is DefaultConnectorImpl &&
    other.dataConnect == dataConnect

  override fun hashCode(): Int =
    Objects.hash(
      "DefaultConnectorImpl",
      dataConnect,
    )

  override fun toString(): String =
    "DefaultConnectorImpl(dataConnect=$dataConnect)"
}



private open class DefaultConnectorGeneratedQueryImpl<Data, Variables>(
  override val connector: DefaultConnector,
  override val operationName: String,
  override val dataDeserializer: DeserializationStrategy<Data>,
  override val variablesSerializer: SerializationStrategy<Variables>,
) : GeneratedQuery<DefaultConnector, Data, Variables> {

  @ExperimentalFirebaseDataConnect
  override fun copy(
    connector: DefaultConnector,
    operationName: String,
    dataDeserializer: DeserializationStrategy<Data>,
    variablesSerializer: SerializationStrategy<Variables>,
  ) =
    DefaultConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: SerializationStrategy<NewVariables>
  ) =
    DefaultConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: DeserializationStrategy<NewData>
  ) =
    DefaultConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is DefaultConnectorGeneratedQueryImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    Objects.hash(
      "DefaultConnectorGeneratedQueryImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "DefaultConnectorGeneratedQueryImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}

private open class DefaultConnectorGeneratedMutationImpl<Data, Variables>(
  override val connector: DefaultConnector,
  override val operationName: String,
  override val dataDeserializer: DeserializationStrategy<Data>,
  override val variablesSerializer: SerializationStrategy<Variables>,
) : GeneratedMutation<DefaultConnector, Data, Variables> {

  @ExperimentalFirebaseDataConnect
  override fun copy(
    connector: DefaultConnector,
    operationName: String,
    dataDeserializer: DeserializationStrategy<Data>,
    variablesSerializer: SerializationStrategy<Variables>,
  ) =
    DefaultConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: SerializationStrategy<NewVariables>
  ) =
    DefaultConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: DeserializationStrategy<NewData>
  ) =
    DefaultConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is DefaultConnectorGeneratedMutationImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    Objects.hash(
      "DefaultConnectorGeneratedMutationImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "DefaultConnectorGeneratedMutationImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}



private class CreateCityMutationImpl(
  connector: DefaultConnector
):
  CreateCityMutation,
  DefaultConnectorGeneratedMutationImpl<
      CreateCityMutation.Data,
      CreateCityMutation.Variables
  >(
    connector,
    CreateCityMutation.Companion.operationName,
    CreateCityMutation.Companion.dataDeserializer,
    CreateCityMutation.Companion.variablesSerializer,
  )


private class CreateCourseMutationImpl(
  connector: DefaultConnector
):
  CreateCourseMutation,
  DefaultConnectorGeneratedMutationImpl<
      CreateCourseMutation.Data,
      CreateCourseMutation.Variables
  >(
    connector,
    CreateCourseMutation.Companion.operationName,
    CreateCourseMutation.Companion.dataDeserializer,
    CreateCourseMutation.Companion.variablesSerializer,
  )


private class CreateCourseGroupMutationImpl(
  connector: DefaultConnector
):
  CreateCourseGroupMutation,
  DefaultConnectorGeneratedMutationImpl<
      CreateCourseGroupMutation.Data,
      CreateCourseGroupMutation.Variables
  >(
    connector,
    CreateCourseGroupMutation.Companion.operationName,
    CreateCourseGroupMutation.Companion.dataDeserializer,
    CreateCourseGroupMutation.Companion.variablesSerializer,
  )


private class CreateStudentMutationImpl(
  connector: DefaultConnector
):
  CreateStudentMutation,
  DefaultConnectorGeneratedMutationImpl<
      CreateStudentMutation.Data,
      CreateStudentMutation.Variables
  >(
    connector,
    CreateStudentMutation.Companion.operationName,
    CreateStudentMutation.Companion.dataDeserializer,
    CreateStudentMutation.Companion.variablesSerializer,
  )


private class DeleteCourseGroupMutationImpl(
  connector: DefaultConnector
):
  DeleteCourseGroupMutation,
  DefaultConnectorGeneratedMutationImpl<
      DeleteCourseGroupMutation.Data,
      DeleteCourseGroupMutation.Variables
  >(
    connector,
    DeleteCourseGroupMutation.Companion.operationName,
    DeleteCourseGroupMutation.Companion.dataDeserializer,
    DeleteCourseGroupMutation.Companion.variablesSerializer,
  )


private class ListAllCourseGroupsQueryImpl(
  connector: DefaultConnector
):
  ListAllCourseGroupsQuery,
  DefaultConnectorGeneratedQueryImpl<
      ListAllCourseGroupsQuery.Data,
      Unit
  >(
    connector,
    ListAllCourseGroupsQuery.Companion.operationName,
    ListAllCourseGroupsQuery.Companion.dataDeserializer,
    ListAllCourseGroupsQuery.Companion.variablesSerializer,
  )



// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
