
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

import com.google.firebase.dataconnect.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.util.UUID


@Serializable
  public data class CityKey(
  
    val id:
    UUID
  ) {
    
    
  }

  @Serializable
  public data class CourseGroupKey(
  
    val id:
    UUID
  ) {
    
    
  }

  @Serializable
  public data class CourseKey(
  
    val id:
    UUID
  ) {
    
    
  }

  @Serializable
  public data class StudentKey(
  
    val id:
    UUID
  ) {
    
    
  }


// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
