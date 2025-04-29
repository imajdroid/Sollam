
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


@file:UseSerializers(
  
    UUIDSerializer::class,
  
)


package connectors.default


import com.google.firebase.dataconnect.QueryRef
import com.google.firebase.dataconnect.QueryResult
import com.google.firebase.dataconnect.generated.GeneratedQuery
import com.google.firebase.dataconnect.serializers.UUIDSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.serializer
import java.util.UUID
import kotlinx.coroutines.flow.filterNotNull as _flow_filterNotNull
import kotlinx.coroutines.flow.map as _flow_map


public interface ListAllCourseGroupsQuery :
    GeneratedQuery<
      DefaultConnector,
      ListAllCourseGroupsQuery.Data,
      Unit
    >
{
  

  
    @Serializable
  public data class Data(
  
    val courseGroups:
    List<CourseGroupsItem>
  ) {
    
      
        @Serializable
  public data class CourseGroupsItem(
  
    val id:
    UUID,
    val courseGroupName:
    String,
    val orderingIndex:
    Double
  ) {
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "ListAllCourseGroups"

    public val dataDeserializer: DeserializationStrategy<Data> =
      serializer()

    public val variablesSerializer: SerializationStrategy<Unit> =
      serializer()
  }
}

public fun ListAllCourseGroupsQuery.ref(
  
): QueryRef<
    ListAllCourseGroupsQuery.Data,
    Unit
  > =
  ref(
    
      Unit
    
  )

public suspend fun ListAllCourseGroupsQuery.execute(
  
  ): QueryResult<
    ListAllCourseGroupsQuery.Data,
    Unit
  > =
  ref(
    
  ).execute()


  public fun ListAllCourseGroupsQuery.flow(
    
    ): Flow<ListAllCourseGroupsQuery.Data> =
    ref(
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }


// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
