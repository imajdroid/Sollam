
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

import com.google.firebase.dataconnect.MutationRef
import com.google.firebase.dataconnect.MutationResult
import com.google.firebase.dataconnect.generated.GeneratedMutation
import com.google.firebase.dataconnect.serializers.UUIDSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.serializer
import java.util.UUID


public interface CreateCourseMutation :
    GeneratedMutation<
      DefaultConnector,
      CreateCourseMutation.Data,
      CreateCourseMutation.Variables
    >
{
  
    @Serializable
  public data class Variables(
  
    val title:
    String,
    val coverUrl:
    String,
    val desc:
    String,
    val duration:
    String,
    val courseGroupId:
    UUID,
    val instructorId:
    UUID
  ) {
    
    
  }
  

  
    @Serializable
  public data class Data(
  @SerialName("course_insert")
    val key:
    CourseKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "createCourse"

    public val dataDeserializer: DeserializationStrategy<Data> =
      serializer()

    public val variablesSerializer: SerializationStrategy<Variables> =
      serializer()
  }
}

public fun CreateCourseMutation.ref(
  
    title: String,coverUrl: String,desc: String,duration: String,courseGroupId: UUID,instructorId: UUID,
  
  
): MutationRef<
    CreateCourseMutation.Data,
    CreateCourseMutation.Variables
  > =
  ref(
    
      CreateCourseMutation.Variables(
        title=title,coverUrl=coverUrl,desc=desc,duration=duration,courseGroupId=courseGroupId,instructorId=instructorId,
  
      )
    
  )

public suspend fun CreateCourseMutation.execute(
  
    title: String,coverUrl: String,desc: String,duration: String,courseGroupId: UUID,instructorId: UUID,
  
  
  ): MutationResult<
    CreateCourseMutation.Data,
    CreateCourseMutation.Variables
  > =
  ref(
    
      title=title,coverUrl=coverUrl,desc=desc,duration=duration,courseGroupId=courseGroupId,instructorId=instructorId,
  
    
  ).execute()



// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
