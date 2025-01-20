package vip.cdms.allaymc.nodejs

import com.caoccao.javet.interop.NodeRuntime
import com.caoccao.javet.interop.V8Host
import com.caoccao.javet.interop.callback.JavetBuiltInModuleResolver
import com.caoccao.javet.interop.options.NodeRuntimeOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object NodeEnvironment {
    val Logger: Logger = LoggerFactory.getLogger(this::class.java)

    init {
        NodeRuntimeOptions.NODE_FLAGS.isExperimentalSqlite = true
    }
    val NodeRuntime = V8Host.getNodeInstance().createV8Runtime<NodeRuntime>().apply {
        // TODO
    }

    val BuiltInModuleResolver = JavetBuiltInModuleResolver()
}
