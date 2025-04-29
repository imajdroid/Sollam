
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
import com.google.firebase.dataconnect.generated.GeneratedMutation
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.serializer


public interface CreateCityMutation :
    GeneratedMutation<
      DefaultConnector,
      CreateCityMutation.Data,
      CreateCityMutation.Variables
    >
{
  
    @Serializable
  public data class Variables(
  
    val cityName:
    String
  ) {
    
    
  }
  

  
    @Serializable
  public data class Data(
  @SerialName("city_insert")
    val key:
    CityKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "createCity"

    public val dataDeserializer: DeserializationStrategy<Data> =
        serializer()

    public val variablesSerializer: SerializationStrategy<Variables> =
        serializer()
  }
}

public fun CreateCityMutation.ref(
  
    cityName: String,
  
  
): MutationRef<
    CreateCityMutation.Data,
    CreateCityMutation.Variables
  > =
  ref(
    
      CreateCityMutation.Variables(
        cityName=cityName,
  
      )
    
  )

public suspend fun CreateCityMutation.execute(
  
    cityName: String,
  
  
  ): MutationResult<
    CreateCityMutation.Data,
    CreateCityMutation.Variables
  > =
  ref(
    
      cityName=cityName,
  
    
  ).execute()



// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
