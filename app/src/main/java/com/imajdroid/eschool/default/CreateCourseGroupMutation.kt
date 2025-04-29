
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

import com.google.firebase.dataconnect.MutationRef
import com.google.firebase.dataconnect.MutationResult
import com.google.firebase.dataconnect.OptionalVariable
import com.google.firebase.dataconnect.generated.GeneratedMutation
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.serializer


public interface CreateCourseGroupMutation :
    GeneratedMutation<
      DefaultConnector,
      CreateCourseGroupMutation.Data,
      CreateCourseGroupMutation.Variables
    >
{
  
    @Serializable
  public data class Variables(
  
    val courseGroupName:
    String,
    val orderingIndex:
    OptionalVariable<Double?>
  ) {
    
    
      
      @DslMarker public annotation class BuilderDsl

      @BuilderDsl
      public interface Builder {
        public var courseGroupName: String
        public var orderingIndex: Double?
        
      }

      public companion object {
        @Suppress("NAME_SHADOWING")
        public fun build(
          courseGroupName: String,
          block_: Builder.() -> Unit
        ): Variables {
          var courseGroupName= courseGroupName
            var orderingIndex: OptionalVariable<Double?> =
                OptionalVariable.Undefined
            

          return object : Builder {
            override var courseGroupName: String
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { courseGroupName = value_ }
              
            override var orderingIndex: Double?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { orderingIndex = OptionalVariable.Value(value_) }
              
            
          }.apply(block_)
          .let {
            Variables(
              courseGroupName=courseGroupName,orderingIndex=orderingIndex,
            )
          }
        }
      }
    
  }
  

  
    @Serializable
  public data class Data(
  @SerialName("courseGroup_insert")
    val key:
    CourseGroupKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "createCourseGroup"

    public val dataDeserializer: DeserializationStrategy<Data> =
        serializer()

    public val variablesSerializer: SerializationStrategy<Variables> =
        serializer()
  }
}

public fun CreateCourseGroupMutation.ref(
  
    courseGroupName: String,
  
    block_: CreateCourseGroupMutation.Variables.Builder.() -> Unit
  
): MutationRef<
    CreateCourseGroupMutation.Data,
    CreateCourseGroupMutation.Variables
  > =
  ref(
    
      CreateCourseGroupMutation.Variables.build(
        courseGroupName=courseGroupName,
  
    block_
      )
    
  )

public suspend fun CreateCourseGroupMutation.execute(
  
    courseGroupName: String,
  
    block_: CreateCourseGroupMutation.Variables.Builder.() -> Unit
  
  ): MutationResult<
    CreateCourseGroupMutation.Data,
    CreateCourseGroupMutation.Variables
  > =
  ref(
    
      courseGroupName=courseGroupName,
  
    block_
    
  ).execute()



// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
