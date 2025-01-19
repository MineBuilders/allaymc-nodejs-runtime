package vip.cdms.allaymc.nodejs

import com.caoccao.javet.interop.NodeRuntime
import com.caoccao.javet.interop.V8Host

object NodeRuntime {
    val Instance = V8Host.getNodeInstance().createV8Runtime<NodeRuntime>()
}
